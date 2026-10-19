package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Request;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Systems;



@Service
@Qualifier("ModifySystemNameRequestService")
public class ModifySystemNameRequestService implements ModifyRequestService {

    @Override 
    public void modify (Request request) {
        request.setSystemName(Systems.SERVICE_1);

        HttpEntity<Request> requestEntity = new HttpEntity<>(request);

        new RestTemplate().exchange(
            "http://localhost:8084/requests",
                HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<Request>() {

                }
        );
    }

}

