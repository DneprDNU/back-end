package org.dnu.filestorage.controller;

import org.dnu.filestorage.controller.generic.GenericController;
import org.dnu.filestorage.model.Speciality;
import org.dnu.filestorage.service.dao.SpecialityDAO;
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
@RequestMapping("/rest/speciality")
public class SpecialityController extends GenericController<SpecialityDAO, Speciality> {

    @Autowired
    public SpecialityController(SpecialityDAO dao) {
        super(dao);
    }

    @RequestMapping(params = "facultyId")
    @ResponseBody
    public List<Speciality> listAllByFacultyId(@RequestParam("facultyId") Long facultyId) {
        return getDao().getSpecialitiesByFacultyId(facultyId);
    }
}
