package com.pewde.authservice.mapper;

import com.pewde.authservice.entity.User;
import com.pewde.authservice.request.SignRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User mapSignRequestToUser(SignRequest signRequest);

}
