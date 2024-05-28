package com.Royal.Main.service.util;

import com.Royal.Main.persistence.entity.Role;
import com.Royal.Main.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUtility {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleUtility(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getUserRole(){
        return roleRepository.findById(1L).get();
    }

    public Role getMerchantRole(){
        return roleRepository.findById(2L).get();
    }
}
