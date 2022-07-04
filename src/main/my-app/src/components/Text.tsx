import React from "react";

type MyProps = {
    fun: Function
    label: string
}

type MyState = {
    content: string
}

class Text extends React.Component<MyProps,MyState>{
    state: MyState = {content: ""}

    change = (value: string) => {
        this.props.fun(value)
        this.setState({content: value})
    }

    render(){
        return (
            <div>
                <label>{this.props.label}</label>
                <input type="input" placeholder="Enter Levels" className="input_box" onChange={e => this.change(e.target.value)} value={this.state.content}/>
            </div>
        );
    }
}

export default Text;