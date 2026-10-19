package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays, int year) {
        boolean isLeapYear = Year.isLeap(year);
        int daysInYear = isLeapYear ? 366 : 365;
        return salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;
    }

    @Override
    public double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays, int year) {
        if (!positions.isManager()) {
            throw new IllegalArgumentException("Квартальная премия доступна только для управленцев (manager).");
        }
        boolean isLeapYear = Year.isLeap(year);
        int daysInYear = isLeapYear ? 366 : 365;
        return (salary * bonus * (daysInYear / 4.0) * positions.getPositionCoefficient()) / workDays;
    }
}