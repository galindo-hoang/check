import React from "react";
import Chart from 'react-apexcharts'

type MyProps = {
}

type MyState = {
}


class BartChart extends React.Component<MyProps,MyState>{
    render() {
        return (
            <React.Fragment>
                <Chart
                type="bar"
                width={1380}
                height= {700}
                series={[
                    { name:"Social Medial Subscriber",
                    data:[6578,6787,3245,9876,2324,5123,2435]
                }
                ]}
                options={{
                    title:{text:"BarChar"},
                }}
                >

                </Chart>
            </React.Fragment>
        )
    }
}
export default BartChart;