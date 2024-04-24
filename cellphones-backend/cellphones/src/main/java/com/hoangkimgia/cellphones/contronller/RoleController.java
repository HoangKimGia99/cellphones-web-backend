package com.hoangkimgia.cellphones.contronller;

import com.hoangkimgia.cellphones.entity.Role;
import com.hoangkimgia.cellphones.exception.UserAlreadyExistsException;
import com.hoangkimgia.cellphones.service.RoleServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleServiceImpl roleServiceImpl;
    @PostMapping("/create-new-role")
    public ResponseEntity<String> createRole(@RequestBody Role theRole){
        try{
            roleServiceImpl.createRole(theRole);
            return ResponseEntity.ok("New role created successfully!");
        }catch(UserAlreadyExistsException re){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(re.getMessage());
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleServiceImpl.getRoles(),HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{rolesId}")
    public ResponseEntity<String> deleteRoles(@PathVariable ("rolesId")Long rolesId ){
        try{
            roleServiceImpl.deleteRoles(rolesId);
            return ResponseEntity.ok("Roles deleted successfully");
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
