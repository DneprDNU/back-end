package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Speciality;
import org.dnu.filestorage.service.dao.SpecialityDAO;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author demyura
 * @since 14.11.14
 */
@Repository
@Transactional
public class SpecialityDAOImpl extends GenericDAOImpl<Speciality> implements SpecialityDAO {

}
