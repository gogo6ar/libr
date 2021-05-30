package com.isd.libr.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookActionInfoDto {
    private String firstName;
    private String lastName;
    private String date;


   public static BookActionInfoDto from(String firstName,String lastName,String date){
       BookActionInfoDto result = new BookActionInfoDto();
       result.setFirstName(firstName);
       result.setLastName(lastName);
       result.setDate(date);
       return result;
   }


}
