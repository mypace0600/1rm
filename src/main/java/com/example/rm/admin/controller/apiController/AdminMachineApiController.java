package com.example.rm.admin.controller.apiController;

import com.example.rm.entity.Machine;
import com.example.rm.machine.service.MachineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminMachineApiController {

    private final MachineService machineService;

    @RequestMapping(
            value = "/admin/machine",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> machineInsert(@ModelAttribute Machine machine){
        machineService.save(machine);
        return ResponseEntity.ok("Resource saved successfully");
    }

    @RequestMapping(
            value = "/admin/machine/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<String>  machineDelete(
            @PathVariable("id") int id
    ){
        machineService.delete(Long.valueOf(id));
        return ResponseEntity.ok("Resource deleted successfully");
    }

    @RequestMapping(
            value = "/admin/machine/{id}",
            method = RequestMethod.PUT
    )
    public ResponseEntity<String>  machineUpdate(
            @PathVariable int id, @ModelAttribute Machine machine
    ) {
        machine.setId(Long.valueOf(id));
        machineService.update(machine);
        return ResponseEntity.ok("Resource updated successfully");
    }


}
