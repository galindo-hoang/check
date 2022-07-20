import React from "react";
import Axios from "./api/pmo";
import BarChart from "./component/BarChart";
import Filtering from "./component/Filtering";
import Moment from "moment";

class App extends React.Component {
    state = {
        dataList: [],
        month: {},
        project: {},
    }
    constructor(props) {
        super(props);
        this.onFilteringSubmit = this.onFilteringSubmit.bind(this)
    }
    async onFilteringSubmit(filters) {
        const response = await Axios.get('/filtering/chart', {
            params: {
                projects: filters.Project,
                start: filters.Start,
                end: filters.End
            }
        })
        this.storeRequestData(filters)
        this.setState({dataList: response.data,})
    }

    storeRequestData(filters){
        let s = Moment(filters.Start,"YYYY-dd-MM")
        let e = Moment(filters.End,"YYYY-dd-MM")
        let requestMonth = new Map()
        let i = 2
        while (s.isSameOrBefore(e)){
            requestMonth.set(i,s.format("MMM-YYYY"))
            i += 2
            s.add(1, "month")
        }
        let requestProject = new Map()
        let listProject = filters.Project.split(',')
        for(let j = 0; j < listProject.length; ++ j){
            requestProject.set((j+1)*2,listProject[j])
        }

        this.setState({month: requestMonth, project: requestProject,})
    }

    render () {
        return (
            <div>
                <Filtering onSubmitForm = {this.onFilteringSubmit}/>
                <BarChart
                    dataEmployee = {this.state.dataList}
                    requestMonth = {this.state.month}
                    requestProject = {this.state.project}/>
            </div>
        )
    }
}
export default App;
