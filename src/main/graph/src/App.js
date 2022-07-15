import React from "react";
import Axios from "./api/pmo";
import BarChart from "./component/BarChart";
import Filtering from "./component/Filtering";

class App extends React.Component {
    state = { dataList: [] }
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
        this.setState({dataList: response.data})
    }

    render () {
        return (
            <div>
                <Filtering onSubmitForm = {this.onFilteringSubmit}/>
                <BarChart dataEmployee = {this.state.dataList}/>
            </div>
        )
    }
}
export default App;
