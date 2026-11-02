package org.example.expanse_tracking.Dao;

import lombok.RequiredArgsConstructor;
import org.example.expanse_tracking.Dto.Response.BudgetAlertResponse;
import org.example.expanse_tracking.Dto.Response.BudgetStatusResponse;
import org.example.expanse_tracking.Entity.Budget;
import org.example.expanse_tracking.Entity.User;
import org.example.expanse_tracking.Exception.ResourceNotFoundException;
import org.example.expanse_tracking.Repository.BudgetRepository;
import org.example.expanse_tracking.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BudgetAlertDaoImpl implements BudgetAlertDao {

    private final BudgetStatusDao budgetStatusDao;
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    @Override
    public BudgetAlertResponse getBudgetAlert(Long budgetId) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Budget budget = budgetRepository.findByIdAndUser(budgetId, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Budget not found"));

        BudgetStatusResponse status =
                budgetStatusDao.getBudgetStatus(budgetId);

        BigDecimal percentageUsed = status.percentageUsed();
        String alertLevel;
        String message;

        if (percentageUsed.compareTo(BigDecimal.valueOf(100)) >= 0) {
            alertLevel = "EXCEEDED";
            message = "You have exceeded your budget for " + budget.getCategory() + ".";
        } else if (percentageUsed.compareTo(BigDecimal.valueOf(80)) >= 0) {
            alertLevel = "WARNING";
            message = "You have used at least 80% of your budget for " + budget.getCategory() + ".";
        } else {
            alertLevel = "NORMAL";
            message = "Your spending is within the budget for " + budget.getCategory() + ".";
        }

        return new BudgetAlertResponse(
                budget.getId(),
                budget.getCategory(),
                budget.getAmount(),
                status.spentAmount(),
                percentageUsed,
                alertLevel,
                message
        );
    }
}
