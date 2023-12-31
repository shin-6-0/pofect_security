import React from "react";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogContentText,
  Card,
  Grid,
  Typography,
} from "@mui/material";
import {
  Chart as ChartJS,
  ArcElement,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Bar, Doughnut } from "react-chartjs-2";

ChartJS.register(
  ArcElement,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const LotChart = ({ open, handleClose, sumValue }) => {
  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
      title: {
        display: true,
        text: "기투입 차트",
      },
    },
  };
  const labels = ["970", "1270", "1570", "1570~"];
  const data = {
    labels,
    datasets: [
      {
        label: "1공장",
        data: [
          sumValue.width_9701_sum,
          sumValue.width_12701_sum,
          sumValue.width_15701_sum,
          sumValue.width_over_15701_sum,
        ],
        backgroundColor: "rgba(255, 99, 132, 0.5)",
      },
      {
        label: "2공장",
        data: [
          sumValue.width_9702_sum,
          sumValue.width_12702_sum,
          sumValue.width_15702_sum,
          sumValue.width_over_15702_sum,
        ],
        backgroundColor: "rgba(53, 162, 235, 0.5)",
      },
    ],
  };
  const optionStand = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
      title: {
        display: true,
        text: "투입대기 차트",
      },
    },
  };
  const dataStand = {
    labels,
    datasets: [
      {
        label: "대기량",
        data: [
          sumValue.width_970_stand_sum,
          sumValue.width_1270_stand_sum,
          sumValue.width_1570_stand_sum,
          sumValue.width_over_1570_stand_sum,
        ],
        backgroundColor: "rgba(255, 99, 132, 0.5)",
      },
    ],
  };
  const test = {
    labels: ["투입대기", "기투입"],
    datasets: [
      {
        label: "합계량",
        data: [
          sumValue.width_970_stand_sum +
            sumValue.width_1270_stand_sum +
            sumValue.width_1570_stand_sum +
            sumValue.width_over_1570_stand_sum,
          sumValue.width_9701_sum +
            sumValue.width_12701_sum +
            sumValue.width_15701_sum +
            sumValue.width_over_15701_sum +
            sumValue.width_9702_sum +
            sumValue.width_12702_sum +
            sumValue.width_15702_sum +
            sumValue.width_over_15702_sum,
        ],
        backgroundColor: ["rgba(255, 99, 132, 0.2)", "rgba(54, 162, 235, 0.2)"],
        borderColor: ["rgba(255, 99, 132, 1)", "rgba(54, 162, 235, 1)"],
        borderWidth: 1,
      },
    ],
  };
  return (
    <Dialog
      open={open}
      onClose={handleClose}
      sx={{ width: "100%" }}
      maxWidth="xl"
    >
      <DialogTitle>
        <Grid item xs={12}>
          <Typography variant="h5">출강Lot 전체 차트</Typography>
        </Grid>
      </DialogTitle>
      <DialogContent>
        <DialogContentText
          style={{
            height: "100%",
          }}
        >
          <div
            style={{
              display: "flex",
              flexDirection: "row",
              justifyContent: "flex-end",
              alignItems: "center",
              paddingTop: "15px",
            }}
          >
            <Card>
              <Bar options={optionStand} data={dataStand} />
            </Card>
            <Card>
              <Bar options={options} data={data} />
            </Card>
            <Card>
              <Doughnut data={test} />
            </Card>
          </div>
        </DialogContentText>
      </DialogContent>
    </Dialog>
  );
};

export default LotChart;
