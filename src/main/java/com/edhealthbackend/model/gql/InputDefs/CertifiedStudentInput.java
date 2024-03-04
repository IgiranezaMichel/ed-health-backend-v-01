package com.edhealthbackend.model.gql.InputDefs;

import com.edhealthbackend.model.CertifiedStudent;
import lombok.Getter;
import lombok.Setter;

public class CertifiedStudentInput extends CertifiedStudent {
@Getter @Setter private long studentId;
@Getter @Setter private long certificateId;
}
