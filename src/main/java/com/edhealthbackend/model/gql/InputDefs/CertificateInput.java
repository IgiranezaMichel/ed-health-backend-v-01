package com.edhealthbackend.model.gql.InputDefs;
import com.edhealthbackend.model.Certificate;
import lombok.Getter;
import lombok.Setter;

public class CertificateInput extends Certificate{
@Getter @Setter private long trainingId;
@Getter @Setter private long accountHolderId;
}
