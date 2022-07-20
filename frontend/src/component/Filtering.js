import React from 'react'
import Axios from "../api/pmo";
import Multiselect from "multiselect-react-dropdown";

class Filtering extends React.Component {
    state =  {
        projectList: '',
        startDate: null,
        endDate: null,
        options: []
    }

    constructor(props) {
        super(props);
        this.multiselectRef = React.createRef()
        this.onFormSubmit = this.onFormSubmit.bind(this)
        this.getListProjectGroup().then(r => {
            let dataList = []
            for (const element of r.data) {
                dataList.push({name: element})
            }
            this.setState({options: dataList})
        }).catch(err => {
            console.log(err)
        })
    }

    /**
     * It gets a list of projects from the server and stores them in the state.
     */
    async getListProjectGroup(){
        return await Axios.get('/filtering/projects')
    }

    /**
     * The function takes the selected items from the multiselect component and joins them into a string, then it takes the
     * start and end dates and formats them into a string that can be used in the SQL query
     * @param event - The event that triggered the function.
     */
    onFormSubmit(event) {
        event.preventDefault()
        let list = []
        for(const element of this.multiselectRef.current.getSelectedItems()) {
            list.push(element.name)
        }
        const projects = list.join(',')
        const [yearStart, monthStart, dayStart] = this.state.startDate.split('-')
        const [yearEnd, monthEnd, dayEnd] = this.state.endDate.split('-')
        this.props.onSubmitForm({
            Project: projects,
            Start: yearStart+"-"+dayStart+"-"+monthStart,
            End: yearEnd+"-"+dayEnd+"-"+monthEnd,})
    }

    /**
     * The function takes the selected projects from the multiselect component, the start date and end date from the input
     * fields, and then sends a POST request to the server with the data
     * @returns A form with a multiselect dropdown, two date inputs, and a submit button.
     */
    render() {
        return (
            <div style={{display:"block", marginLeft:"auto", marginRight: "auto", width: "400px"}}>
                <form onSubmit={this.onFormSubmit}>
                    <div style={{display: "block"}}>
                        <label>Projects</label>
                        <Multiselect
                            options={this.state.options}
                            displayValue="name"
                            ref = {this.multiselectRef}/>
                    </div>
                    <div style={{display:"flex", justifyContent:"space-evenly", marginTop: "5px"}}>
                        <div style={{display: "inline"}}>
                            <label style={{display: "flex", justifyContent: "center", alignItems: "center"}}>Date Start</label>
                            <input style={{display:"flex"}}
                                type="date"
                                onChange={e => this.setState({startDate: e.target.value})}
                            />
                        </div>
                        <div style={{display: "inline"}}>
                            <label style={{display: "flex", justifyContent: "center", alignItems: "center"}}>End Start</label>
                            <input style={{display:"block"}}
                                type="date"
                                onChange={e => this.setState({endDate: e.target.value})}
                            />
                        </div>
                    </div>
                    <div>
                        <input style={{display:"block", marginTop: "10px", marginLeft:"auto", marginRight: "auto", width: "100%"}}
                            type="submit"
                            value="Submit"
                        />
                    </div>
                </form>
            </div>
        )
    }
}

export default Filtering