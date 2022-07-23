package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mb.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{

}
