package com.katsshura.ze.challenge.domain;

import com.katsshura.ze.challenge.domain.interfaces.PartnerDataManagement;
import com.katsshura.ze.challenge.domain.interfaces.PartnerServiceDefinition;
import com.katsshura.ze.challenge.domain.mock.PartnerDataManagementMock;
import com.katsshura.ze.challenge.domain.models.Partner;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

public class PartnerServiceTest {

    private PartnerDataManagement partnerDataManagement;

    @BeforeAll
    void setUp() {
        partnerDataManagement = new PartnerDataManagementMock();
    }

}
