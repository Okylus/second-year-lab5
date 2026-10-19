package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Response;
import ru.arkhipov.MySecondTestAppSpringBoot.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;


@Service 
@Qualifier ("ModifySystemTimeResponseService")

public class ModifySystemTimeResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtil.getDateTimeFormatter()
                    .format(new Date()));
        return response;
    }
    
}
