import React from 'react'

class Filtering extends React.Component {
    state =  {
        projectList: '',
        startDate: null,
        endDate: null
    }

    onFormSubmit = event => {
        event.preventDefault()
        let list = this.state.projectList.split(';')
        for(let i = 0; i<list.length; i++){
            list[i] = list[i].trimStart().trimEnd()
        }
        list = list.join(',')
        const [yearStart, monthStart, dayStart] = this.state.startDate.split('-')
        const [yearEnd, monthEnd, dayEnd] = this.state.endDate.split('-')
        this.props.onSubmitForm({
            Project: list,
            Start: yearStart+"-"+dayStart+"-"+monthStart,
            End: yearEnd+"-"+dayEnd+"-"+monthEnd,})
    }

    render() {
        return (
            <div>
                <form onSubmit={this.onFormSubmit}>
                    <div>
                        <label>Projects</label>
                        <input
                            type="text"
                            value={this.state.projectList}
                            onChange={e => this.setState({projectList: e.target.value.trimEnd().trimStart()})}
                        />
                    </div>
                    <div>
                        <label>Date Start</label>
                        <input
                            type="date"
                            onChange={e => this.setState({startDate: e.target.value})}
                            />
                    </div>
                    <div>
                        <label>End Start</label>
                        <input
                            type="date"
                            onChange={e => this.setState({endDate: e.target.value})}
                        />
                    </div>
                    <div>
                        <input
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