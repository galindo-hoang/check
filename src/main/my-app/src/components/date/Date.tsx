import React from "react";

type MyProps = {
    fun: Function
    label: string
}

type MyState = {
    content: string
}

class Date extends React.Component<MyProps, MyState>{

    onChange = (e: String) => {
        this.props.fun(e)
    }

    render() {
        return (
            <div>
                <label>{this.props.label}</label>
                <input type="date" onChange={(e) => this.onChange(e.target.value)}/>
            </div>
        )
    }
}

export default Date