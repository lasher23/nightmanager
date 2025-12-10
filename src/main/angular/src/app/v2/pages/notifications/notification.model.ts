export interface NotificationItem {
  id: string;
  title: string;
  message: string;
  receivedAt: string; // ISO
  read: boolean;
  url?: string;
}
