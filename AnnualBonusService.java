package ru.arkhipov.MySecondTestAppSpringBoot.service;

import ru.arkhipov.MySecondTestAppSpringBoot.model.Positions;

public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays, int year);
    double calculateQuarterlyBonus(Positions positions, double salary, double bonus, int workDays, int year);
}