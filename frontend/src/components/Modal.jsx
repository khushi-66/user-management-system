export default function Modal({
  show,
  onClose,
  title,
  message,
  type = "success", 
  darkmode
}) {
  if (!show) return null;

  const headerClass =
    type === "success"
      ? "bg-success text-light"
      : type === "error"
      ? "bg-danger text-light"
      : "bg-primary text-light";

  return (
    <>
      <div className="modal fade show d-block" tabIndex="-1">
        <div className="modal-dialog">
          <div className="modal-content">

            <div className={`modal-header ${headerClass}`}>
              <h5 className="modal-title">{title}</h5>
              <button className="btn-close" onClick={onClose}></button>
            </div>

            <div className={`modal-body ${darkmode ? "bg-dark text-light" : ""}`}>
              <p>{message}</p>
            </div>

            <div className={`modal-footer ${darkmode ? "bg-dark" : ""}`}>
              <button
                className={`btn ${
                  darkmode ? "btn-outline-light" : "btn-primary"
                }`}
                onClick={onClose}
              >
                OK
              </button>
            </div>

          </div>
        </div>
      </div>

      <div className="modal-backdrop fade show"></div>
    </>
  );
}