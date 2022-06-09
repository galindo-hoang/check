import React from 'react';
import './App.css'
import Text from "./components/text/Text";
import hibernate from "./api/hibernate";
import Date from "./components/date/Date";
import Table from './components/table/Table';


export type employee = {
    visa: string,
    consolidatedHours: bigint,
    name: string
}

type hourReportCriteria = {
    level: string
    subProject: string
    start: string
    end: string
    employee: Array<employee>
}

type props = {}

class App extends React.Component<props,hourReportCriteria>{
    state: hourReportCriteria = {
        level: "",
        subProject: "",
        start: "",
        end: "",
        employee: Array()
    };

    level = (value: string) => {
        this.setState({level:value})
    }
    subproject = (value: string) => {
        this.setState({subProject:value})
    }
    startDate = (value: string) => {
        this.setState({start:value})
    }
    endDate = (value: string) => {
        this.setState({end:value})
    }

    onSubmit = async (e: Event) => {
        e.preventDefault()
        const response = await hibernate.get('/test', {
            params: {
                levels: this.state.level,
                start: this.state.start,
                end: this.state.end,
                projects: this.state.subProject,
            }
        })
        response.data.forEach((emp:undefined) => {
            console.log(response.data)
            let b:Array<employee> = []
            // @ts-ignore
            b.push({visa: emp.visa,name: emp.name,consolidatedHours: emp.consolidatedHours})
            this.setState({employee:b})
        })

    }


    render() {
        return (
            <div className="App">
                <form className="form" onSubmit={(e) => this.onSubmit(e.nativeEvent)}>
                    <span className="heading">Hibernate</span>
                    <Text fun={this.level} label="Level"/>
                    <Text fun={this.subproject} label="Sub-Project"/>
                    <Date fun={this.startDate} label="Start-Date"/>
                    <Date fun={this.endDate} label="End-Date"/>
                    <input type="submit" value="Submit"/>
                    <Table list={this.state.employee}/>
                </form>
            </div>
        );
    }

}

export default App;
