import Graph3D from 'react-graph3d-vis'
import React from 'react'
import Moment from "moment";


class BarChart extends React.Component{

    static projectList = new Map()
    static months = new Map()

    /**
     * It takes a map and a search value, and returns the value associated with the search value if it exists, or an empty
     * string if it doesn't
     * @param map - The map to search through
     * @param searchValue - The value you want to search for in the map.
     * @returns The value of the key that is being searched for.
     */
    static getValueByKey(map, searchValue) {
        if(map.has(searchValue)){
            return map.get(searchValue)
        }else return ""
    }

    /**
     * It takes a map and a value, and returns the key that corresponds to that value
     * @param map - The map to search through
     * @param value - The value you want to find the key of.
     * @returns The key of the map that has the value of the argument passed in.
     */
    getKeyByValue(map,value){
        if(map.size !== undefined){
            for(let [k] of map){
                if(map.get(k) === value) return k
            }
        }
        return 0
    }


    /**
     * This function is used to process the data that is passed in from the parent component
     */
    processData(){
        BarChart.projectList.clear()
        BarChart.months.clear()
        if(this.props.requestProject.size !== undefined){
            for (let [k,v] of this.props.requestProject){
                BarChart.projectList.set(k,v)
            }
        }

        if(this.props.requestMonth.size !== undefined){
            for (let [k,v] of this.props.requestMonth){
                BarChart.months.set(k,v)
            }
        }
    }

    /**
     * It takes the data from the props and creates a new array with the data in the format that the HeatMap component
     * needs
     * @returns an array of objects.
     */
    generateData() {
        let data =[]
        for(const element of this.props.dataEmployee) {
            if(element.hours > 0){
                data.push({
                    x: this.getKeyByValue(this.props.requestProject,element.projectGroup),
                    y: this.getKeyByValue(this.props.requestMonth, Moment(element.yearMonth).format("MMM-YYYY")),
                    z: element.hours,
                })
            }
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
            return BarChart.getValueByKey(BarChart.projectList, value)
        },
        yValueLabel: function (value){
            return BarChart.getValueByKey(BarChart.months, value)
        },
        xMax: this.props.requestProject.size >= 3 ? this.props.requestProject.size * 2: 6,
        yMax: this.props.requestMonth.size >= 3 ? this.props.requestMonth.size * 2: 24,
        xMin: 0,
        yMin: 0,
        zMin: 0,
        keepAspectRatio: true,
        verticalRatio: 0.5,
    };

    /**
     * The function first processes the data, then generates the data, and if the data is empty, it returns an empty div,
     * otherwise it returns a div with a Graph3D component that has the data and options as props
     * @returns A 3D graph of the data.
     */
    render() {
        this.processData()
        let data = this.generateData()
        if(data.length === 0) return (<div></div>)
        else return (
            <div style={{display: "flex", justifyContent: "center", alignItems: "center"}}>
                <Graph3D data={data} options={this.options}/>
            </div>
        )
    }
}

export default BarChart