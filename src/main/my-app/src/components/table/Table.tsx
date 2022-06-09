import React, {Fragment} from "react";
import {employee} from "../../App";
type MyState = {}
type MyProps = {
    list: employee[]
}
export default class Table extends React.Component<MyProps,MyState>{


    render() {
        return (
            <div>
                <table>
                    <thead>
                        <tr>
                            <th>Visa</th>
                            <th>Name</th>
                            <th>Hours</th>
                        </tr>
                    </thead>

                    <tbody>
                    {
                        this.props.list.map((object, index) => {
                            return (<tr key={index}>
                                <td>{object.visa}</td>
                                <td>{object.name}</td>
                                <td>{object.consolidatedHours.toString()}</td>
                            </tr>)
                        })
                    }
                    </tbody>
                    <tfoot></tfoot>
                </table>
            </div>
        )
    }
}