import Graph3D from 'react-graph3d-vis'
import React from 'react'


class BarChart extends React.Component{

    static projectList = new Map()
    static months = new Map()

    static getByValue(map, searchValue) {
        for (let [key, value] of map.entries()) {
            if (value === searchValue)
                return key;
        }
        return ""
    }

    processData(){
        BarChart.projectList.clear()
        BarChart.months.clear()
        let iterProject = 0,
            iterMonths = 0
        for(let i = 0; i < this.props.dataEmployee.length; ++i){
            if(!BarChart.projectList.has(this.props.dataEmployee[i].projectGroup)){
                iterProject += 2
                BarChart.projectList.set(this.props.dataEmployee[i].projectGroup, iterProject)
            }
            if(!BarChart.projectList.has(this.props.dataEmployee[i].yearMonth)){
                iterMonths += 2
                BarChart.months.set(this.props.dataEmployee[i].yearMonth, iterMonths)
            }
        }
    }

    generateData() {
        let data =[]
        for(let i = 0; i < this.props.dataEmployee.length; ++i){
            data.push({
                x: BarChart.projectList.get(this.props.dataEmployee[i].projectGroup),
                y: BarChart.months.get(this.props.dataEmployee[i].yearMonth),
                z: this.props.dataEmployee[i].hours,
                style: { "fill":"red"}
            })
        }
        return data
    }

    options = {
        width:  '600px',
        height: '600px',
        style: 'bar',
        showPerspective: true,
        showGrid: true,
        showShadow: false,
        tooltip: function (point){
            return point.z + " hours"
        },
        xValueLabel: function (value) {
            return BarChart.getByValue(BarChart.projectList,value)
        },
        yValueLabel: function (value) {
            return BarChart.getByValue(BarChart.months,value)
        },
        xMax: 0,
        yMax: 0,
        zMin: 0,
        keepAspectRatio: true,
        verticalRatio: 0.5,
    };

    render() {
        this.processData()
        let data = this.generateData()
        this.options.xMax = BarChart.projectList.size >= 3 ? BarChart.projectList.size : 6
        this.options.yMax = BarChart.months.size >= 3 ? BarChart.months.size : 6
        if(data.length === 0) return (<div></div>)
        else return (
            <Graph3D data={data} options={this.options}/>
        )
    }
}

export default BarChart