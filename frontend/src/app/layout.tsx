import type { Metadata } from 'next'
import '../styles/globals.css'
import Navbar from '@/components/layout/Navbar'
import Footer from '@/components/layout/Footer'

export const metadata: Metadata = {
  title: 'Abdulla Avloniy Muzeyi',
  description: 'Abdulla Avloniy nomidagi muzey — buyuk ma\'rifatparvар va pedagog Abdulla Avloniy hayoti va ijodiga bag\'ishlangan.',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="uz">
      <body>
        <Navbar />
        <main>{children}</main>
        <Footer />
      </body>
    </html>
  )
}
