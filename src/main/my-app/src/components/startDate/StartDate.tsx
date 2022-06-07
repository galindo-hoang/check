import React from "react";

type MyProps = {
    fun: Function
}
type MyState = {
    content: string
}


class StartDate extends React.Component<MyProps,MyState>{
    state: MyState = {content: ""}

    change = (value: string) => {
        this.setState({content: value})
        this.props.fun(this.state.content)
    }

    render(){
        return (
            <div>
                <label>Start-Date</label>
                <input type="input" placeholder="Enter StartDate"  onChange={e => this.change(e.target.value)} value={this.state.content}/>
            </div>
        );
    }
}

export default StartDate;