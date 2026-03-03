'use client'
import Link from 'next/link'

export default function Footer() {
  return (
    <footer style={{
      background: 'linear-gradient(135deg, #0a1829 0%, #112548 100%)',
      color: 'rgba(255,255,255,0.75)',
      paddingTop: '64px',
      borderTop: '1px solid rgba(201,168,76,0.2)',
    }}>
      <div className="container">
        <div style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fit, minmax(220px, 1fr))',
          gap: '48px',
          paddingBottom: '48px',
        }}>

          {/* Brand */}
          <div>
            <div style={{
              fontFamily: 'var(--font-display)',
              fontSize: '22px',
              fontWeight: '700',
              color: '#fff',
              marginBottom: '8px',
            }}>Abdulla Avloniy</div>
            <div style={{
              fontFamily: 'var(--font-mono)',
              fontSize: '10px',
              color: '#C9A84C',
              letterSpacing: '3px',
              textTransform: 'uppercase',
              marginBottom: '16px',
            }}>Muzeyi · 1934</div>
            <p style={{ fontSize: '14px', lineHeight: '1.8', maxWidth: '260px' }}>
              Buyuk ma'rifatparvar, shoir va pedagog Abdulla Avloniy xotirasini abadiylashtiruvchi milliy muzey.
            </p>
          </div>

          {/* Links */}
          <div>
            <div style={{
              fontFamily: 'var(--font-display)',
              fontSize: '16px',
              fontWeight: '600',
              color: '#C9A84C',
              marginBottom: '20px',
            }}>Bo'limlar</div>
            {[
              { href: '/about', label: 'Muzey Haqida' },
              { href: '/gallery', label: 'Galereya' },
              { href: '/resources', label: 'Manbalar' },
              { href: '/news', label: 'Yangiliklar' },
              { href: '/faq', label: 'FAQ' },
              { href: '/contact', label: "Bog'lanish" },
            ].map(link => (
              <Link key={link.href} href={link.href} style={{
                display: 'block',
                fontSize: '14px',
                color: 'rgba(255,255,255,0.65)',
                padding: '5px 0',
                transition: 'color 0.2s',
              }}
              onMouseEnter={e => (e.currentTarget.style.color = '#C9A84C')}
              onMouseLeave={e => (e.currentTarget.style.color = 'rgba(255,255,255,0.65)')}
              >
                → {link.label}
              </Link>
            ))}
          </div>

          {/* Contact */}
          <div>
            <div style={{
              fontFamily: 'var(--font-display)',
              fontSize: '16px',
              fontWeight: '600',
              color: '#C9A84C',
              marginBottom: '20px',
            }}>Manzil</div>
            <div style={{ fontSize: '14px', lineHeight: '2', color: 'rgba(255,255,255,0.65)' }}>
              <div>📍 Toshkent shahri</div>
              <div>🕐 Du–Yak: 9:00 – 17:00</div>
              <div>📞 +998 (71) 000-00-00</div>
              <div>✉️ info@avloniy-museum.uz</div>
            </div>
          </div>
        </div>

        {/* Bottom bar */}
        <div style={{
          borderTop: '1px solid rgba(255,255,255,0.08)',
          padding: '20px 0',
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          flexWrap: 'wrap',
          gap: '12px',
        }}>
          <div style={{ fontSize: '13px', color: 'rgba(255,255,255,0.4)' }}>
            © 2024 Abdulla Avloniy Muzeyi. Barcha huquqlar himoyalangan.
          </div>
          <Link href="/admin" style={{
            fontFamily: 'var(--font-mono)',
            fontSize: '11px',
            color: 'rgba(255,255,255,0.25)',
            letterSpacing: '1px',
            transition: 'color 0.2s',
          }}
          onMouseEnter={e => (e.currentTarget.style.color = '#C9A84C')}
          onMouseLeave={e => (e.currentTarget.style.color = 'rgba(255,255,255,0.25)')}
          >
            Admin →
          </Link>
        </div>
      </div>
    </footer>
  )
}
