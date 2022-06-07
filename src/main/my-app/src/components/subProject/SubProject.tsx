import React, {Fragment} from "react";

type MyProps = {
    fun: Function
}
type MyState = {
    content: string
}


class SubProject extends React.Component<MyProps,MyState>{
    state: MyState = {content: ""}

    change = (value: string) => {
        this.setState({content: value})
        this.props.fun(this.state.content)
    }

    render(){
        return (
            <div>
                <label>Sub-Projects</label>
                <input type="input" placeholder="Enter SubProject"  onChange={e => this.change(e.target.value)} value={this.state.content}/>
            </div>
        );
    }
}

export default SubProject;