import { useState } from 'react'
import heroImg from './assets/hero.png'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import './App.css'
import { Link, NavLink, Route, Routes, useNavigate, useParams } from "react-router";

function Navbar() {
  return (
    <nav className="navbar">
      <Link to="/" className="brand">
        Raja Online Service
      </Link>

      <div className="nav-links">
        <NavLink to="/">Home</NavLink>
        <NavLink to="/services">Services</NavLink>
      </div>
    </nav>
  );
}

function Home() {
  const navigate = useNavigate();

  return (
    <main className="hero">
      <p className="eyebrow">YOUR TRUSTED SERVICE PARTNER</p>

      <h1>Welcome to Raja Online Service</h1>

      <p className="hero-description">
        Simple, convenient and reliable online services for everyone.
      </p>

      <button
        className="primary-button"
        onClick={() => navigate("/services")}
      >
        Explore Services
      </button>
    </main>
  );
}

function Services() {
  const navigate = useNavigate();

  return (
    <main className="page-container">
      <h1>Our Services</h1>
      <p className="page-description">
        Select a service to continue.
      </p>

      <div className="card-grid">
        <button
          className="service-card"
          onClick={() => navigate("/pan-services")}
        >
          <span className="card-icon">🪪</span>
          <h2>PAN Service</h2>
          <p>Apply for a new PAN card or request a PAN renewal.</p>
          <span className="card-link">View PAN Services →</span>
        </button>

        <button
          className="service-card"
          onClick={() => navigate("/aadhaar-services")}
        >
          <span className="card-icon">📄</span>
          <h2>Aadhaar Service</h2>
          <p>Continue to Aadhaar-related service requests.</p>
          <span className="card-link">View Aadhaar Services →</span>
        </button>
      </div>
    </main>
  );
}

function PanServices() {
  const navigate = useNavigate();

  return (
    <main className="page-container">
      <button
        className="back-button"
        onClick={() => navigate("/services")}
      >
        ← Back to Services
      </button>

      <h1>PAN Services</h1>
      <p className="page-description">
        Choose the type of PAN service you require.
      </p>

      <div className="card-grid">
        <button
          className="service-card"
          onClick={() => navigate("/application/new-pan")}
        >
          <span className="card-icon">🆕</span>
          <h2>New PAN Service</h2>
          <p>Submit your details to request a new PAN service.</p>
          <span className="card-link">Apply Now →</span>
        </button>

        <button
          className="service-card"
          onClick={() => navigate("/application/pan-renewal")}
        >
          <span className="card-icon">🔄</span>
          <h2>PAN Renewal Service</h2>
          <p>Submit your details for a PAN renewal request.</p>
          <span className="card-link">Apply Now →</span>
        </button>
      </div>
    </main>
  );
}

function AadhaarServices() {
  const navigate = useNavigate();

  return (
    <main className="page-container">
      <button
        className="back-button"
        onClick={() => navigate("/services")}
      >
        ← Back to Services
      </button>

      <h1>Aadhaar Services</h1>
      <p className="page-description">
        Select the Aadhaar service you need.
      </p>

      <div className="card-grid">
        <button
          className="service-card"
          onClick={() => navigate("/application/aadhaar-service")}
        >
          <span className="card-icon">📋</span>
          <h2>Aadhaar Service Request</h2>
          <p>Provide your contact details to request assistance.</p>
          <span className="card-link">Continue →</span>
        </button>
      </div>
    </main>
  );
}

function ApplicationForm() {
  const { serviceType } = useParams();
  const navigate = useNavigate();

  const serviceNames = {
    "new-pan": "New PAN Service",
    "pan-renewal": "PAN Renewal Service",
    "aadhaar-service": "Aadhaar Service"
  };

  const backendServiceTypes = {
  "new-pan": "NEW",
  "pan-renewal": "RENEWAL",
  "aadhaar-service": "AADHAAR"
};

  const serviceName = serviceNames[serviceType];
  const applicationType = backendServiceTypes[serviceType];

  const [formData, setFormData] = useState({
    firstName: "",
    email: "",
    mobileNumber: ""
  });

  const [submitted, setSubmitted] = useState(false);

  function handleChange(event) {
    const { name, value } = event.target;

    setFormData((previousData) => ({
      ...previousData,
      [name]: value
    }));

    setSubmitted(false);
  }

 async function handleSubmit(event) {
    event.preventDefault();
    const requestData = {
    fullName: formData.firstName,
    email: formData.email,
    mobileNumber: formData.mobileNumber,
    applicationType: applicationType
  };

  try {
    const response = await fetch(
      "http://localhost:8080/api/panApplication/create",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(requestData)
      }
    );

    if (!response.ok) {
      throw new Error("Failed to submit PAN application");
    }

    setSubmitted(true);

    const result = await response.text();
    console.log("Backend response:", result);

  } catch (error) {
    console.error("Error submitting application:", error);
    alert("Unable to submit application. Please try again.");
  }

    // Frontend demonstration only.
    // Connect this form to your Spring Boot API later.
  }

  if (!serviceName) {
    return (
      <main className="page-container">
        <h1>Service not found</h1>
        <button
          className="primary-button"
          onClick={() => navigate("/services")}
        >
          Back to Services
        </button>
      </main>
    );
  }

  return (
    <main className="form-page">
      <section className="form-card">
        <button
          type="button"
          className="back-button"
          onClick={() => navigate(-1)}
        >
          ← Back
        </button>

        <div className="form-heading">
          <span className="card-icon">📝</span>
          <h1>{serviceName}</h1>
          <p>Enter your details to submit a service request.</p>
        </div>

        {submitted ? (
          <div className="success-message">
            <h2>Thank you, {formData.firstName}!</h2>
            <p>
              Your form has been validated in the frontend demo.
              No application has been saved to the server yet.
            </p>
            <button
              className="primary-button"
              onClick={() => navigate("/services")}
            >
              Back to Services
            </button>
          </div>
        ) : (
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <label htmlFor="firstName">First Name</label>
              <input
                id="firstName"
                name="firstName"
                type="text"
                placeholder="Enter your first name"
                value={formData.firstName}
                onChange={handleChange}
                required
                minLength={2}
                maxLength={100}
              />
            </div>

            <div className="form-group">
              <label htmlFor="email">Mail ID</label>
              <input
                id="email"
                name="email"
                type="email"
                placeholder="Enter your email address"
                value={formData.email}
                onChange={handleChange}
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="mobileNumber">Mobile Number</label>
              <input
                id="mobileNumber"
                name="mobileNumber"
                type="tel"
                inputMode="numeric"
                placeholder="Enter your 10-digit mobile number"
                value={formData.mobileNumber}
                onChange={handleChange}
                pattern="[0-9]{10}"
                title="Enter a 10-digit mobile number"
                required
              />
            </div>

            <button
              type="submit"
              className="primary-button submit-button"
            >
              Submit Application
            </button>
          </form>
        )}
      </section>
    </main>
  );
}

function NotFound() {
  return (
    <main className="page-container">
      <h1>Page not found</h1>
      <Link className="card-link" to="/">
        Return to Home
      </Link>
    </main>
  );
}

export default function App() {
  return (
    <div className="app">
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/services" element={<Services />} />
        <Route path="/pan-services" element={<PanServices />} />
        <Route path="/aadhaar-services" element={<AadhaarServices />} />
        <Route
          path="/application/:serviceType"
          element={<ApplicationForm />}
        />
        <Route path="*" element={<NotFound />} />
      </Routes>

      <footer className="footer">
        <p>© {new Date().getFullYear()} Raja Online Service</p>
      </footer>
    </div>
  );
}