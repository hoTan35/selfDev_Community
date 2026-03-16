import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [message, setMessage] = useState<string>('Loading...')

  useEffect(() => {
    fetch('/api/hello')
      .then(res => res.text())
      .then(data => setMessage(data))
      .catch(err => {
        console.error(err);
        setMessage('Error fetching from backend');
      });
  }, [])

  return (
    <div className="container">
      <h1>SelfDev Community</h1>
      <div className="card">
        <p>Backend Message: <strong>{message}</strong></p>
      </div>
      <p className="read-the-docs">
        Spring Boot + React 블로그 게시판 프로젝트가 시작되었습니다!
      </p>
    </div>
  )
}

export default App
