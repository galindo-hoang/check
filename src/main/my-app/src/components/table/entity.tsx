import React from "react";

export default class Entity extends React.Component<any, any>{


    render() {
        return (
            <div>
                <table className="table-auto">
                    <tr>
                        <th>Visa</th>
                        <th>Name</th>
                        <th>Hours</th>
                    </tr>
                </table>
            </div>
        )
    }
}