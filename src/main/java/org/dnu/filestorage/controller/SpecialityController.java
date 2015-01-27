package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.data.model.Speciality;
import org.dnu.filestorage.data.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author demyura
 * @since 15.10.14
 */
@Controller
@Api(value = "specialities", description = "Endpoint for speciality management")
@RequestMapping("/rest/speciality")
public class SpecialityController extends GenericController<SpecialityService, Speciality> {

    @Autowired
    public SpecialityController(SpecialityService service) {
        super(service);
    }

    @RequestMapping(params = "facultyId")
    @ResponseBody
    public List<Speciality> listAllByFacultyId(@RequestParam("facultyId") Long facultyId) {
        return getService().getSpecialitiesByFacultyId(facultyId);
    }
}
