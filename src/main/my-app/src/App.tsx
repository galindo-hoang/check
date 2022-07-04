import React from 'react';
import './App.css'
import Text from './components/Text';
import BartChart from './components/BartChart';
import hibernate from "./api/hibernate";


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
                {/* <form className="form" onSubmit={(e) => this.onSubmit(e.nativeEvent)}>
                    <span className="heading">Hibernate</span>
                    <Text fun={this.level} label="Level"/>
                    <Text fun={this.subproject} label="Sub-Project"/>
                    <input type="submit" value="Submit"/>
                </form> */}
                <BartChart/>
            </div>
        );
    }

}

export default App;
