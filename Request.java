package ru.arkhipov.MySecondTestAppSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    /**
     * Уникальный идентификатор сообщения
     */
    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32)
    private String uid;

    /**
     * Уникальный идентификатор операции
     */
    private String operationUid;

    /**
     * Имя системы отправителя
     */
    private Systems systemName;

    /**
     * Время создания сообщения в системе
     */
    private String systemTime;

    /**
     * Источник запроса
     */
    private String source;

    /**
     * Должность сотрудника
     */
    private Positions position;

    /**
     * Заработная плата
     */
    private Double salary;

    /**
     * Бонус
     */
    private Double bonus;

    /**
     * Количество рабочих дней в году
     */
    private Integer workDays;

    /**
     * Идентификатор коммуникации
     */
    private Integer communicationId;

    /**
     * Идентификатор шаблона
     */
    private Integer templateId;

    /**
     * Код продукта
     */
    private Integer productCode;

    /**
     * СМС код
     */
    private Integer smsCode;

    @Override
    public String toString() {
        return "Request{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName=" + systemName +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position=" + position +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", workDays=" + workDays +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}