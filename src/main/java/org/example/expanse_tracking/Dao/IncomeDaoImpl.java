
package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Exception.ResourceNotFoundException;
import org.example.expanse_tracking.Dto.Request.IncomeRequest;
import org.example.expanse_tracking.Dto.Response.IncomeResponse;
import org.example.expanse_tracking.Entity.Income;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Mapper.IncomeMapper;
import org.example.expanse_tracking.Repository.IncomeRepository;
import org.example.expanse_tracking.Repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IncomeDaoImpl implements IncomeDao {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;
    private final IncomeMapper incomeMapper;

    // Get the currently logged-in user
    private User getLoggedInUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );
    }

    // Add income
    @Override
    public IncomeResponse addIncome(IncomeRequest request) {

        User user = getLoggedInUser();

        Income income = incomeMapper.toEntity(request);

        income.setUser(user);

        Income savedIncome = incomeRepository.save(income);

        return incomeMapper.toResponse(savedIncome);
    }

    // Get all income records of logged-in user
    @Override
    @Transactional(readOnly = true)
    public List<IncomeResponse> getAllIncome() {

        User user = getLoggedInUser();

        return incomeRepository
                .findByUserOrderByDateDesc(user)
                .stream()
                .map(incomeMapper::toResponse)
                .toList();
    }

    // Get income by ID
    @Override
    @Transactional(readOnly = true)
    public IncomeResponse getIncomeById(Long id) {

        User user = getLoggedInUser();

        Income income = incomeRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new RuntimeException("Income not found")
                );

        return incomeMapper.toResponse(income);
    }

    // Update income
    @Override
    public IncomeResponse updateIncome(
            Long id,
            IncomeRequest request
    ) {

        User user = getLoggedInUser();

        Income income = incomeRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new RuntimeException("Income not found")
                );

        incomeMapper.updateEntity(request, income);

        Income updatedIncome = incomeRepository.save(income);

        return incomeMapper.toResponse(updatedIncome);
    }

    // Delete income
    @Override
    public void deleteIncome(Long id) {

        User user = getLoggedInUser();

        Income income = incomeRepository
                .findByIdAndUser(id, user)
                .orElseThrow(() ->
                        new RuntimeException("Income not found")
                );

        incomeRepository.delete(income);
    }
}
