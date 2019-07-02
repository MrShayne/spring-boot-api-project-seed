package com.company.project.service.impl;

import com.company.project.dao.SponsorMapper;
import com.company.project.model.Sponsor;
import com.company.project.service.SponsorService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/06/13.
 */
@Service
@Transactional
public class SponsorServiceImpl extends AbstractService<Sponsor> implements SponsorService {
    @Resource
    private SponsorMapper sponsorMapper;

}
