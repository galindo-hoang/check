package com.example.ex.mapper

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.model.EmployeeMetaInfo
import org.springframework.stereotype.Component
import javax.persistence.PersistenceContext

@Component
class EmployeeMetaInfoMapper {
    fun entityToDto(employeeMetaInfo: EmployeeMetaInfo): EmployeeMetaInfoDto {
        return EmployeeMetaInfoDto.Builder()
            .chPrdPercent(employeeMetaInfo.chPrdPercent)
            .chPrd(employeeMetaInfo.chPrd)
            .Name(employeeMetaInfo.name)
            .Visa(employeeMetaInfo.visa)
            .absence(employeeMetaInfo.absence)
            .calculated(employeeMetaInfo.calculated)
            .calculatedDivision(employeeMetaInfo.calculatedDivision)
            .calculatedIsMgr(employeeMetaInfo.calculatedIsMgr)
            .calculatedOverview(employeeMetaInfo.calculatedOverview)
            .calculatedVecLeft(employeeMetaInfo.calculatedVecLeft)
            .cumulDiff(employeeMetaInfo.cumulDiff)
            .difference(employeeMetaInfo.difference)
            .division(employeeMetaInfo.division)
            .entrance(employeeMetaInfo.entrance)
            .firstName(employeeMetaInfo.firstName)
            .forFait(employeeMetaInfo.forfait)
            .group(employeeMetaInfo.groupsa)
            .holidays(employeeMetaInfo.holidays)
            .illness(employeeMetaInfo.illness)
            .lastName(employeeMetaInfo.lastName)
            .mgr(employeeMetaInfo.isMgr)
            .onBoarding(employeeMetaInfo.onboarding)
            .others(employeeMetaInfo.others)
            .partTime(employeeMetaInfo.partTime)
            .resignation(employeeMetaInfo.resignation?:0)
            .sort(employeeMetaInfo.sort)
            .trainings(employeeMetaInfo.trainings)
            .vecLeft(employeeMetaInfo.vecLeft)
            .vnEntry(employeeMetaInfo.vnEntry)
            .workedHours(employeeMetaInfo.workedHours)
            .workingHours(employeeMetaInfo.workingHours)
            .build()
    }




}