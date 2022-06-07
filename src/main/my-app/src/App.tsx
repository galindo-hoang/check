import React from 'react';
import './App.css'
import Level from "./components/levelInput/Level";
import SubProject from "./components/subProject/SubProject";
import StartDate from "./components/startDate/StartDate";
import EndDate from "./components/endDate/EndDate";
import hibernate from "./api/hibernate";

type hourReportCriteria = {
    level: string
    subProject: string
    start: string
    end: string
}
type props = {

}

class App extends React.Component<props,hourReportCriteria>{
    state: hourReportCriteria = {
        level: "",
        subProject: "",
        start: "",
        end: ""
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

    onSubmit = async () => {
        const respone = await hibernate.get('/test', {
            params: {
                level: this.state.level
            }
        })
    }

    render() {
        return (
            <div className="App">
                <form onSubmit={this.onSubmit}>
                    <span className="heading">Hibernate</span>
                    <Level fun={this.level}/>
                    <SubProject fun={this.subproject}/>
                    <StartDate fun={this.startDate}/>
                    <EndDate fun={this.endDate}/>
                </form>
            </div>
        );
    }

}

export default App;
