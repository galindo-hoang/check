import React from "react";
import {employee} from "../../App";
type MyState = {}
type MyProps = {
    list: employee[]
}
export default class Table extends React.Component<MyProps,MyState>{

    visualize() {
        console.log("aaaa")
        return (
            <tbody>
            {
                this.props.list.map(function (object,index){
                    return (
                        <tr key={index}>
                            <td>{object.visa}</td>
                            <td>{object.name}</td>
                            <td>{object.consolidatedHours.toString()}</td>
                        </tr>
                    )
                })
            }
            </tbody>
        )
    }

    render() {
        return (
            <div>
                <table>
                    <tr>
                        <th>Visa</th>
                        <th>Name</th>
                        <th>Hours</th>
                    </tr>
                    {this.visualize()}
                </table>
            </div>
        )
    }
}