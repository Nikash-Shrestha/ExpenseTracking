package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Request.ExpenseRequest;
import org.example.expanse_tracking.Dto.Response.ExpenseResponse;
import org.example.expanse_tracking.Entity.Expense;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Exception.ResourceNotFoundException;
import org.example.expanse_tracking.Mapper.ExpenseMapper;
import org.example.expanse_tracking.Repository.ExpenseRepository;
import org.example.expanse_tracking.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseDaoImpl implements ExpenseDao {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseMapper expenseMapper;

    private User getLoggedInUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    @Override
    public ExpenseResponse addExpense(ExpenseRequest request) {
        User user = getLoggedInUser();

        Expense expense = expenseMapper.toEntity(request);
        expense.setUser(user);

        Expense savedExpense = expenseRepository.save(expense);

        return expenseMapper.toResponse(savedExpense);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExpenseResponse> getAllExpenses() {
        User user = getLoggedInUser();

        return expenseRepository.findByUserOrderByDateDesc(user)
                .stream()
                .map(expenseMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseResponse getExpenseById(Long id) {
        User user = getLoggedInUser();

        Expense expense = expenseRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense not found"));

        return expenseMapper.toResponse(expense);
    }

    @Override
    public ExpenseResponse updateExpense(Long id, ExpenseRequest request) {
        User user = getLoggedInUser();

        Expense expense = expenseRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense not found"));

        expenseMapper.updateEntity(request, expense);

        Expense updatedExpense = expenseRepository.save(expense);

        return expenseMapper.toResponse(updatedExpense);
    }

    @Override
    public void deleteExpense(Long id) {
        User user = getLoggedInUser();

        Expense expense = expenseRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense not found"));

        expenseRepository.delete(expense);
    }
}