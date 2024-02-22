package com.tobeto.pair8.rules.rental;

import com.tobeto.pair8.core.utilities.exceptions.entityException.MaxRentalDaysExceededException;
import com.tobeto.pair8.core.utilities.exceptions.entityException.EndDateBeforeStartDateException;
import com.tobeto.pair8.core.utilities.exceptions.entityException.SameCarOrUserInAnotherRentalException;
import com.tobeto.pair8.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.tobeto.pair8.core.utilities.constants.RentalConstants.*;

@AllArgsConstructor
@Service
public class RentalBusinessRulesManager implements RentalBusinessRulesService {
    private final RentalRepository rentalRepository;

    @Override
    public void dateControl(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new EndDateBeforeStartDateException(END_DATE_BEFORE_START_DATE_MESSAGE);
        }
        else if (endDate.equals(startDate)) {
            throw new RuntimeException( MIN_RENTAL_DAYS_MESSAGE);
        }
    }


    @Override
    public void availableCar(int carId, int userId, LocalDate startDate, LocalDate endDate) {
        if (rentalRepository.existsByCarIdOrUserIdAndDateRange(carId, userId, startDate, endDate)) {
            throw new SameCarOrUserInAnotherRentalException(SAME_CAR_OR_USER_IN_ANOTHER_RENTAL_MESSAGE);
        }
    }

    @Override
    public void maxRentalDays(LocalDate startDate, LocalDate endDate) {
        if (ChronoUnit.DAYS.between(startDate, endDate) > 25) {
            throw new MaxRentalDaysExceededException(MAX_RENTAL_DAYS_EXCEEDED_MESSAGE );
        }
    }
}
