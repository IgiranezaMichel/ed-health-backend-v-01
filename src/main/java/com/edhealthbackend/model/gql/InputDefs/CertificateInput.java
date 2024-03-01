package com.edhealthbackend.model.gql.InputDefs;

import java.time.LocalDateTime;

import com.edhealthbackend.model.Certificate;

import lombok.Getter;
import lombok.Setter;

public class CertificateInput extends Certificate{
@Getter @Setter private long trainingId;
public CertificateInput(long id,String title,String description,String signature,String stamp,LocalDateTime timeStamp,long trainingId){
    this.setId(id);
    this.setTitle(title);
    this.setDescription(description);
    this.setSignature(signature);
    this.setStamp(stamp);
    this.setTimeStamp(timeStamp);
    this.trainingId=trainingId;
}
}
