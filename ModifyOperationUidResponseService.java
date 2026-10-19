package ru.arkhipov.MySecondTestAppSpringBoot.service;


import org.springframework.beans.factory.annotation.Qualifier;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Response;
import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.UUID;

@Service 
@Qualifier ("ModifyOperationUidResponseService")
public class ModifyOperationUidResponseService implements ModifyResponseService {

    @Override
    public Response modify(Response response) {
        UUID uuid = UUID.randomUUID();
        response.setOperationUid(uuid.toString());
        return response;
    }
    
}
