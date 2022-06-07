import React from "react";
import './InputField.css'

class InputField extends React.Component{
    render(){
        return (
            <form className="input">
                <input type="input" placeholder="Enter a task" className="input_box"/>
                <button className="input_button" type="submit">Submit</button>
            </form>
        );
    }
}

export default InputField;