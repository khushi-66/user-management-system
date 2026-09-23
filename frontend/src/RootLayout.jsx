import { Outlet } from "react-router-dom";
import SessionManager from "./components/SessionManager";

 export default function RootLayout() {

    return (
        <>
            <SessionManager />
            <Outlet />
        </>
    );
}