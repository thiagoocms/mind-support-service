package com.nassau.mind_support_service.validation;

import com.nassau.mind_support_service.domain.Class;
import com.nassau.mind_support_service.domain.Session;
import com.nassau.mind_support_service.exception.BadRequestException;
import com.nassau.mind_support_service.exception.ResourceNotFoundException;
import com.nassau.mind_support_service.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SessionValidation {

    private final ClassRepository classRepository;

    @Autowired
    public SessionValidation(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public void checkOwnerFieldsToCreate(Session entity) throws IOException {
        entity.setId(null);
    }

    public void checkMandatoryFields(Session entity) throws IOException {
        List<String> notInformedFieldsList = new ArrayList<>();

        if (Objects.isNull(entity.getClazz()) || Objects.isNull(entity.getClazz().getId())) {
            notInformedFieldsList.add("Id da classe");
        }

        if (!notInformedFieldsList.isEmpty()) {
            throw new BadRequestException("Campos obrigatórios não informados: " + String.join(", ", notInformedFieldsList));
        }
    }

    public Session checkRelations(Session toPersistEntity) throws Throwable {
        this.checkClass(toPersistEntity);
        return toPersistEntity;
    }

    private Session checkClass(Session entity){
        if (Objects.isNull(entity.getClazz())) {
            return entity;
        }

        if (Objects.isNull(entity.getClazz().getId())) {
            entity.setClazz(null);
            return entity;
        }

        Class foreKey = this.classRepository.findFirstById(entity.getClazz().getId());

        if (foreKey == null) {
            throw new ResourceNotFoundException("Classe nao encontrada");
        }

        entity.setClazz(foreKey);

        return entity;
    }
}