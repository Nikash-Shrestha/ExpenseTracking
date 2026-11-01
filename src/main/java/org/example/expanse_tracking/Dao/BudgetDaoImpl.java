package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Request.BudgetRequest;
import org.example.expanse_tracking.Dto.Response.BudgetResponse;
import org.example.expanse_tracking.Entity.Budget;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Exception.ResourceNotFoundException;
import org.example.expanse_tracking.Mapper.BudgetMapper;
import org.example.expanse_tracking.Repository.BudgetRepository;
import org.example.expanse_tracking.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetDaoImpl implements BudgetDao {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final BudgetMapper budgetMapper;

    private User getLoggedInUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    @Override
    public BudgetResponse addBudget(BudgetRequest request) {
        User user = getLoggedInUser();

        Budget budget = budgetMapper.toEntity(request);
        budget.setUser(user);

        Budget savedBudget = budgetRepository.save(budget);

        return budgetMapper.toResponse(savedBudget);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BudgetResponse> getAllBudgets() {
        User user = getLoggedInUser();

        return budgetRepository.findByUserOrderByStartDateDesc(user)
                .stream()
                .map(budgetMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BudgetResponse getBudgetById(Long id) {
        User user = getLoggedInUser();

        Budget budget = budgetRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        return budgetMapper.toResponse(budget);
    }

    @Override
    public BudgetResponse updateBudget(Long id, BudgetRequest request) {
        User user = getLoggedInUser();

        Budget budget = budgetRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        budgetMapper.updateEntity(request, budget);

        Budget updatedBudget = budgetRepository.save(budget);

        return budgetMapper.toResponse(updatedBudget);
    }

    @Override
    public void deleteBudget(Long id) {
        User user = getLoggedInUser();

        Budget budget = budgetRepository.findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        budgetRepository.delete(budget);
    }
}