package com.edhealthbackend.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarchartDTO<T> {
   private  Long value;
    private T label;  
    public BarchartDTO(long value,T label){
        this.setValue(value);
        this.setLabel(label);
    }
}
