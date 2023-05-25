package com.example.ECommerce.Application.Dto.RequestDto;

import com.example.ECommerce.Application.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {

    String name;

    String emailId;

    String mobileNo;

    Gender gender;
}
