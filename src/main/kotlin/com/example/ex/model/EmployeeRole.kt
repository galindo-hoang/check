package com.example.ex.model

import javax.persistence.*

@Entity
@Table(name = "EmployeeRole")
class EmployeeRole: EntitySuper() {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    @Column(name = "Employee_Nr")
    var employeeNr:String? = null

    @Column(name = "Active")
    var active: String? = null

    @Column(name = "Last")
    var last: String? = null

    @Column(name = "First")
    var first:String? = null

    @Column(name = "Contract")
    var contract:Char? = null

    @Column(name = "Forfait")
    var forfait:Char? = null

    @Column(name = "User_Level")
    var userLevel:String? = null

    @Column(name = "Level")
    var level:Int = 0

    @Column(name = "Sub_Level")
    var subLevel:Int = 0

    @Column(name = "Part_Time")
    var partTime:Double? = null

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "Supervisor", nullable = true, foreignKey = ForeignKey(name = "fk_roleSupervisor_employee"))
    var supervisor:EmployeeMetaInfo? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "Abbreviation", nullable = true, foreignKey = ForeignKey(name = "fk_roleAbbreviation_employee"))
    var abbreviation:EmployeeMetaInfo? = null
}